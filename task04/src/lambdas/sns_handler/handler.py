from commons.log_helper import get_logger
from commons.abstract_lambda import AbstractLambda

_LOG = get_logger(__name__)

class SnsHandler(AbstractLambda):

    def validate_request(self, event) -> dict:
        if 'Records' not in event:
            _LOG.warning("Event does not contain 'Records' key")
            event['Records'] = []
        return event

    def handle_request(self, event, context):
        """
        Process messages from SNS topic
        """
        for record in event.get('Records', []):
            message = record.get('Sns', {}).get('Message', 'No message')
            _LOG.info(f"Received message: {message}")
        return 200

HANDLER = SnsHandler()

def lambda_handler(event, context):
    return HANDLER.lambda_handler(event=event, context=context)