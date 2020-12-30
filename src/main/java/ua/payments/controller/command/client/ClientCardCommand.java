package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Card;
import ua.payments.model.service.CardService;

import javax.servlet.http.HttpServletRequest;

public class ClientCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCardCommand.class);
    private CardService cardService = new CardService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String cardId = request.getParameter("card-id");
        Card card = cardService.findById(Long.parseLong(cardId));
        request.setAttribute("card", card);
        return "/WEB-INF/client/client-card.jsp";
    }
}
