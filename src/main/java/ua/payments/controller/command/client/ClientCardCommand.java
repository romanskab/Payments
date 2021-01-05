package ua.payments.controller.command.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.payments.controller.command.Command;
import ua.payments.model.entity.Card;
import ua.payments.model.entity.User;
import ua.payments.model.service.CardService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ClientCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ClientCardCommand.class);
    private CardService cardService = new CardService();

    @Override
    public String execute(HttpServletRequest request) {
        logger.info("execute() started!");
        String cardId = request.getParameter("card-id");

        if (cardId == null || cardId.equals("")){
            final User user = (User) request.getSession().getAttribute("user");
            System.out.println(user);
            final int userId = user.getId();
            final List<Card> cards = cardService.findByUserId(userId);
            request.setAttribute("cards", cards);
            return "/WEB-INF/client/client-allCards.jsp";
        }

        Card card = cardService.findById(Long.parseLong(cardId));
        request.setAttribute("card", card);
        return "/WEB-INF/client/client-card.jsp";
    }
}
