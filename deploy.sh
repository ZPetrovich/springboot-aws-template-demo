#!/bin/bash
set -e

# ------------------------------------------------------------
# Open Demo Version | Template Hero © 2025
# For educational and evaluation use only.
# To access the full commercial version, visit: https://template-hero.com
# ------------------------------------------------------------

# Load variables from .env
echo "📥 Loading .env..."
export $(grep -v '^#' .env | xargs)

echo "🛠️ Step 0: Building Java app with Maven..."
./mvnw clean package -DskipTests

cd infrastructure
echo "⚙️ Step 1: Generating backend.tf from template..."
envsubst < terraform/backend.tf.tpl > terraform/backend.tf

echo "🧪 Checking if DynamoDB Lock Table exists..."
TABLE_EXISTS=$(aws dynamodb describe-table --table-name "$TF_LOCK_TABLE" >/dev/null 2>&1 && echo "YES" || echo "NO")
echo "Lock Table exists: $TABLE_EXISTS"

echo "🧪 Checking if S3 Bucket exists..."
BUCKET_EXISTS=$(aws s3api head-bucket --bucket "$TF_BUCKET" > /dev/null 2>&1 && echo "YES" || echo "NO")
echo "S3 Bucket exists: $BUCKET_EXISTS"

if [ "$TABLE_EXISTS" == "NO" ] || [ "$BUCKET_EXISTS" == "NO" ]; then
    echo "📦 Step 3: Terraform Init (bootstrap backend)..."
    cd bootstrap
    terraform init -input=false -reconfigure

    echo "🌱 Step 4: Terraform Apply (bootstrap backend)..."
    terraform apply -auto-approve \
    -var="region=$AWS_REGION" \
    -var="bucket_name=$TF_BUCKET" \
    -var="lock_table_name=$TF_LOCK_TABLE"
    cd ..
fi

echo "🚀 Step 5: Terraform init with S3 backend..."
cd terraform
terraform init -input=false -reconfigure \
    -backend-config="region=$AWS_REGION"

echo "🚀 Step 6: Terraform apply with S3 backend..."
terraform apply -input=false -auto-approve \
    -var="region=$AWS_REGION" \
    -var="lambda_function_name=$LAMBDA_FUNCTION_NAME" \
    -var="lambda_zip_path=$LAMBDA_ZIP_PATH" \
    -var="main_class=$MAIN_CLASS" \
    -var="dynamodb_table_name=$DYNAMODB_TABLE_NAME" \
    -var="api_gateway_name=$API_GATEWAY_NAME"
