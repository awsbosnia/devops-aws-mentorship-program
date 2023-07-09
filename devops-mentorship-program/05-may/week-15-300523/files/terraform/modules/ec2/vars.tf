variable "ec2_count" {
  default     = "1"
}

variable "ami_id" {}

variable "instance_type" {
    default = "t3.micro"
}

variable "subnet_id" {}
