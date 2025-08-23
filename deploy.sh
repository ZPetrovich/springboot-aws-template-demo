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

echo "🚀 Step 5: Terraform init..."
cd infrastructure/terraform
terraform init -input=false -reconfigure

echo "🚀 Step 6: Terraform apply..."
terraform apply -input=false -auto-approve \
    -var="region=$AWS_REGION" \
    -var="lambda_function_name=$LAMBDA_FUNCTION_NAME" \
    -var="lambda_zip_path=$LAMBDA_ZIP_PATH" \
    -var="main_class=$MAIN_CLASS" \
    -var="api_gateway_name=$API_GATEWAY_NAME"
