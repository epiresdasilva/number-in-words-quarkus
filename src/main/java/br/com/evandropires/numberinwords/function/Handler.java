package br.com.evandropires.numberinwords.function;

import br.com.evandropires.numberinwords.service.NumberInWordsService;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.util.Map;

public class Handler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

  private static final Logger log = Logger.getLogger(Handler.class);

  @Inject
  NumberInWordsService service;

  ObjectWriter writer = new ObjectMapper().writerFor(Response.class);

  @Override
  public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, final Context context) {
    Map<String, String> query = request.getQueryStringParameters();

    Integer number = Integer.parseInt(query.get("number"));
    String numberInWords = new NumberInWordsService().numberInWords(number);

    Response response = new Response();
    response.setInWords(numberInWords);

    log.info("Processed data");
    context.getLogger().log("Test lambda logger");

    try {
      return new APIGatewayProxyResponseEvent().withBody(writer.writeValueAsString(response)).withStatusCode(200);
    } catch (JsonProcessingException e) {
      log.error("Error processing", e);
      return new APIGatewayProxyResponseEvent().withBody(e.getMessage()).withStatusCode(500);
    }
  }
}
