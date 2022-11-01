import groovy.json.JsonSlurper
import java.util.regex.Matcher
import java.util.regex.Pattern

class JSONParser {

    public String parse(String jsonStr,String key) {
        String[] keys=key.split("\\.")
        JsonSlurper js= new JsonSlurper();
        Map parsedJson=js.parseText(jsonStr)

        String firstKey=keys[0]
        def retValue=parsedJson."$firstKey"
        keys.eachWithIndex {item,index->
            if(index!=0){
                String listIndex=null
                Pattern p = Pattern.compile("\\[(.*)\\]");
                Matcher m = p.matcher(item);
                while(m.find()) {
                    listIndex=m.group(1);
                    item=item.substring(0,item.indexOf('['))
                }
                if(listIndex){
                     retValue = retValue."$item".get(listIndex as Integer)
                }else
                {
                    retValue = retValue."$item"
                }
            }
        }
        retValue
    }


    public static void main(String[] arg){
        String jsonString = '''{"menu": {
                                "id": "file",
                                "tools": {
                                "actions": [
                                            {"id": "new", "title": "New File"},
                                            {"id": "open", "title": "Open File"},
                                            {"id": "close", "title": "Close File"}
                                ],
                                "errors": []
                                }}}'''

        JSONParser jsonParser= new JSONParser()
        assert jsonParser.parse(jsonString,"menu.id")=="file"
        assert jsonParser.parse(jsonString,"menu.tools.actions[0]")=="{id=new, title=New File}"
        assert jsonParser.parse(jsonString,"menu.tools.actions[0].id")=="new"
    }
}
