package com.task04;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SNSEvent;
import com.syndicate.deployment.annotations.lambda.LambdaHandler;
import com.syndicate.deployment.annotations.resources.DependsOn;
import com.syndicate.deployment.model.ResourceType;
import com.syndicate.deployment.model.RetentionSetting;

@LambdaHandler(
    lambdaName = "sns_handler",
    roleName = "sns_handler-role",
    isPublishVersion = true,
    aliasName = "learn",
    logsExpiration = RetentionSetting.SYNDICATE_ALIASES_SPECIFIED
)
@DependsOn(resourceType = ResourceType.SNS_TOPIC, name = "lambda_topic")
public class SnsHandler implements RequestHandler<SNSEvent, Void> {
    public Void handleRequest(SNSEvent event, Context context) {
        for (SNSEvent.SNSRecord record : event.getRecords()) {
            context.getLogger().log("Received message: " + record.getSNS().getMessage());
        }
        return null;
    }
}