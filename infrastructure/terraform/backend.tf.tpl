terraform {

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }

  backend "s3" {
    bucket         = "${TF_BUCKET}"
    key            = "${TF_KEY}"
    region         = "${AWS_REGION}"
    dynamodb_table = "${TF_LOCK_TABLE}"
    encrypt        = true
  }
}
