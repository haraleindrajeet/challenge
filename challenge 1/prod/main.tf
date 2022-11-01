terraform {
  required_version = ">= 0.15"
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.28"
    }
    
  }
}


provider "aws" {
  region = var.region
}

module "autoscaling" {
  source      = "./modules/autoscaling" 
  namespace   = var.namespace           
  ssh_keypair = var.ssh_keypair         

  vpc       = module.networking.vpc     
  sg        = module.networking.sg      
  db_config = module.database.db_config 

}

module "database" {
  source    = "./modules/database" 
  namespace = var.namespace        

  vpc = module.networking.vpc 
  sg  = module.networking.sg  

}

module "networking" {
  source    = "./modules/networking" 
  namespace = var.namespace          
}