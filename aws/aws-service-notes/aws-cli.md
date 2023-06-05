# AWS Command Line Interface (CLI)


## Useful AWS CLI Commands to Note

```bash
$ aws sts decode-authorization-message --encoded-message <message>

$ aws sts get-caller-identity 

$ aws ec2 describe-instances --filters "Name=tag:Name,Values=webserver" --query 'Reservations[*].Instances[*].PublicIpAddress' --output text
```
- [Command completion](https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-completion.html#cli-command-completion-linux)
