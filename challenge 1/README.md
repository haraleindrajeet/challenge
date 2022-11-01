# 3 Tier Resource Structure

- This folder provides sample terraform code for 3 Tier resource creation.
- Modular approach is followed so that it helps in re-usability and maintainability.
- Please Note templates have not been tested, and are just to describe resource definition structure.
- Templates can be made more modular and can be used as lego bricks across various caller stacks.
- Modular templates provides flexibility to update only required stacks.


## Usage 
- AWS terraform commands can be executed to create independent infrastucuters using individual templates or all stacks using main template which has nested stack for each tier.
- Automation can be done from Jenkins or any similar build tools to create / update stacks.