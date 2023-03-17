$ aws ec2 describe-instances --region eu-central-1 --filters "Name=tag:Name,Values=week-5-devops-mentorship-program" "Name=instance-state-name,Values=running" --query 'Reservations[*].Instances[*].[PublicIpAddress]' --output text

