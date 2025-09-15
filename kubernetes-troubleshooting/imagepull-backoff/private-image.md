# Private Image


### Create a Secret by providing Docker credentials on the command line

```
kubectl create secret docker-registry demo --docker-server=<your-registry-server> --docker-username=<your-name> --docker-password=<your-pword> --docker-email=<your-email>
```

### Create a Secret by providing AWS ECR credentials on the command line

```
kubectl create secret docker-registry  \
  --docker-server=${AWS_ACCOUNT}.dkr.ecr.${AWS_REGION}.amazonaws.com \
  --docker-username=AWS \
  --docker-password=$(aws ecr get-login-password) \
  --namespace=default
```