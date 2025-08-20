output "api_url" {
  description = "API Gateway endpoint"
  value       = "${aws_api_gateway_rest_api.api.execution_arn}/prod/api"
}

output "api_invoke_url" {
  description = "API Gateway invoke URL"
  value       = "https://${aws_api_gateway_rest_api.api.id}.execute-api.${var.region}.amazonaws.com/prod"
}
