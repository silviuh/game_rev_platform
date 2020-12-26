package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Genre;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.GenreService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.ParseException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class AppController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GenreService genreService;

    @RequestMapping("/")
    public String viewHomePage(Model model) throws ParseException {
        List<FrontPageGame> games = gameService.getAllGamesFrontPage();

        model.addAttribute("games", games);

        return "index";
    }

    @RequestMapping("/newGame")
    public String viewEnterGamePage(Model model) {
        FrontPageGame game = new FrontPageGame();
        List<Genre> genres = genreService.getAllGenres();

        model.addAttribute("game", game);
        model.addAttribute("genres", genres);

        return "new_game_form";
    }

    @RequestMapping(value = "/saveGame", method = RequestMethod.POST)
    public String saveGame(@ModelAttribute("game") FrontPageGame game) {
        gameService.saveGame(game);

        return "redirect:/";
    }

}
