set -x
awslocal s3api create-bucket --bucket bucket-test-toggles-configurations
awslocal s3api create-bucket --bucket toggles-configurations
set +x