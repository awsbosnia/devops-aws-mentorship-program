#Kreiranje vpc, custom made od nas module
resource "aws_vpc" "main" {
  cidr_block       = "${var.vpc_cidr}"
  instance_tenancy = "${var.tenancy}"

  tags = {
    Name = "main"
  }
}

resource "aws_subnet" "main" {
  vpc_id     = "${var.vpc_id}"
  cidr_block = "${var.subnet_cidr}"

  tags = {
    Name = "Main"
  }
}

output "vpc_id" {
  value       = "${aws_vpc.main.id}"
}

output "subnet_id" {
    value = "${aws_subnet.main.id}"
}