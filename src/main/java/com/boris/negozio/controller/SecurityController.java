package com.boris.negozio.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.boris.negozio.model.Articolo;
import com.boris.negozio.service.ArticoloService;

@Controller
public class SecurityController {

	@Autowired
	private ArticoloService as;

	@RequestMapping(value = "/admin/", method = RequestMethod.GET)
	public ModelAndView admin(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/homeAdmin");
		List<Articolo> listaArticoli = as.getAll();
		mv.addObject("listaArticoli", listaArticoli);
		return mv;
	}

	@RequestMapping(value="/addProd",method = RequestMethod.GET)
	public ModelAndView aggiungiArticolo() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/nuovoProd");
		mv.addObject("articolo",new Articolo());
		return mv;
	}
	
	@RequestMapping(value="/add",method = RequestMethod.POST)
	public ModelAndView aggiungi(Articolo articolo, BindingResult r) {
		as.saveArticolo(articolo);
		return new ModelAndView("redirect:/admin/");
	}

	@RequestMapping(value = "/deleteArt/{id}", method = RequestMethod.GET)
	public ModelAndView removeProd(HttpSession session, @PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/adminHome");
		as.delete(id);
		return new ModelAndView("redirect:/admin/");
	}

	@RequestMapping(value = "/editArt/{id}", method = RequestMethod.GET)
	public ModelAndView updateArt(@PathVariable long id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin/modProdotto");
		mv.addObject("articolo", as.findById(id));
		return mv;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView saveArtModify(@Validated Articolo a, BindingResult r) {
		as.saveArticolo(a);
		return new ModelAndView("redirect:/admin/");
	}

}
