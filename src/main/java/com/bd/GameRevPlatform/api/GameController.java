package com.bd.GameRevPlatform.api;

import com.bd.GameRevPlatform.model.Genre;
import com.bd.GameRevPlatform.model.Review;
import com.bd.GameRevPlatform.model.Userr;
import com.bd.GameRevPlatform.service.GameService;
import com.bd.GameRevPlatform.service.GenreService;
import com.bd.GameRevPlatform.service.ReviewService;
import com.bd.GameRevPlatform.service.UserrService;
import com.bd.GameRevPlatform.service.game.FrontPageGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.List;

/**
 * @author Timofti Gabriel
 */

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserrService userrService;

    @RequestMapping("/main_page/{user_id}")
    public String viewHomePage(Model model, @PathVariable(name = "user_id")int user_id) throws ParseException {
        List<FrontPageGame> games = gameService.getAllGamesFrontPage();
        Userr userr = userrService.getUserById(user_id);

        model.addAttribute("games", games);
        model.addAttribute("user", userr);

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

        return "redirect:/main_page";
    }

    @RequestMapping("/edit/{game_id}")
    public ModelAndView viewEditGamePage(@PathVariable(name = "game_id")int game_id ) {
        ModelAndView modelAndView = new ModelAndView("edit_game_form");
        FrontPageGame frontPageGame = gameService.getGameFrontPage(game_id);
        List<Genre> genres = genreService.getAllGenres();

        modelAndView.addObject("game", frontPageGame);
        modelAndView.addObject("genres", genres);

        return modelAndView;
    }

    @RequestMapping(value = "/updateGame", method = RequestMethod.POST)
    public String updateGame(@ModelAttribute("game") FrontPageGame game) {
        gameService.updateGame(game);

        return "redirect:/main_page";
    }

    @RequestMapping("/delete/{game_id}")
    public String deleteGame(@PathVariable(name = "game_id")int game_id ) {
        gameService.deleteGame(game_id);

        return "redirect:/main_page";
    }

    @RequestMapping("/game/{game_id}")
    public ModelAndView viewGame(@PathVariable(name = "game_id")int game_id) {
        ModelAndView modelAndView = new ModelAndView("game_form");
        List<Review> reviews = reviewService.getReviewsByGameId(game_id);
        FrontPageGame frontPageGame = gameService.getGameFrontPage(game_id); //for displaying game title

        modelAndView.addObject("reviews", reviews);
        modelAndView.addObject("game", frontPageGame);

        return modelAndView;
    }

}
