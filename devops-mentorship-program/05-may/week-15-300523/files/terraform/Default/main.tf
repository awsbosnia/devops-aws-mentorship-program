
#Kreiramo aws_vpc 
resource "aws_vpc" "draganvpc" {
  cidr_block = "10.0.0.0/16"

  tags = {
    Name = "DraganovVPCDemo"
  }
}

#Kreiramo aws_subnet za aws_vpc
resource "aws_subnet" "dragan_subnet_1" {
  vpc_id     = aws_vpc.draganvpc.id
  cidr_block = "10.0.1.0/24"

  tags = {
    Name = "DraganovSubnet1"
  }
}

data "aws_ami" "amzn-linux-2023-ami" {
  most_recent = true
  owners      = ["amazon"]

  filter {
    name   = "name"
    values = ["al2023-ami-2023.*-x86_64"]
  }
}

resource "aws_instance" "draganWebServer" {
  ami           = data.aws_ami.amzn-linux-2023-ami.id
  instance_type = "t2.micro"

  network_interface {
    network_interface_id = aws_network_interface.dragan-network-ec2-interface.id
    device_index         = 0
  }

  tags = {
    Name = "DraganovWebServer"
  }
}

resource "aws_network_interface" "dragan-network-ec2-interface" {
  subnet_id       = aws_subnet.dragan_subnet_1.id
  private_ips     = ["10.0.1.10"]

  tags = {
    Name = "Draganov_primarni_interfajs"
  }
}