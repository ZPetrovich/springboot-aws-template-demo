# Load variables from .env
echo "📥 Loading .env..."
export $(grep -v '^#' .env | xargs)

cd infrastructure/terraform
terraform destroy -auto-approve -lock=false \
    -var="region=$AWS_REGION" \
    -var="lambda_function_name=$LAMBDA_FUNCTION_NAME" \
    -var="lambda_zip_path=$LAMBDA_ZIP_PATH" \
    -var="main_class=$MAIN_CLASS" \
    -var="api_gateway_name=$API_GATEWAY_NAME"
