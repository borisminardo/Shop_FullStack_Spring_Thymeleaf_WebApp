package com.boris.negozio.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.boris.negozio.businesscomponent.Carrello;
import com.boris.negozio.model.Articolo;
import com.boris.negozio.model.Ordine;
import com.boris.negozio.model.OrdineArticolo;
import com.boris.negozio.model.Utente;
import com.boris.negozio.service.ArticoloService;
import com.boris.negozio.service.OrdineArticoloService;
import com.boris.negozio.service.OrdineService;
import com.boris.negozio.service.UtenteService;

@Controller
@Scope("session")
public class ProgettoController {
	@Autowired
	private UtenteService us;
	
	@Autowired
	private ArticoloService as;
	
	@Autowired
	private OrdineService os;
	
	@Autowired
	private OrdineArticoloService oas;
	@Autowired
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public ModelAndView home(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		mv.addObject("_carrello", (Carrello)session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente)session.getAttribute("utente_log"));
		return mv;
	}
	
	/*-----------------Gestione utente */
	@RequestMapping(value="/registrazione",method = RequestMethod.GET)
	public ModelAndView registraUtente(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("registrazione");
		mv.addObject("utente",new Utente());
		mv.addObject("_carrello", (Carrello)session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente)session.getAttribute("utente_log"));
		return mv;
	}
	
	@RequestMapping(value="/registrazione",method = RequestMethod.POST)
	public ModelAndView saveUtente(Utente u, BindingResult r) {
		us.saveUtente(u);
		return new ModelAndView("redirect:/login");
	}
	
	/*-----------------Login utente */
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public ModelAndView loginUtente(HttpSession session) {
		if(session.getAttribute("utente_log") !=null) {
			return new ModelAndView("redirect:/logout");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		mv.addObject("utente",new Utente());
		mv.addObject("_carrello", (Carrello)session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente)session.getAttribute("utente_log"));
		return mv;
	}
	
	@RequestMapping(value="/edit/{utente}",method= RequestMethod.POST)
	public ModelAndView update(HttpSession session, Utente utente) {
		if(session.getAttribute("utente_log") ==null) {
			return new ModelAndView("redirect:/modificaUtente");
		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("modificaUtente");
		mv.addObject("utente",us.findByUsername(utente.getUsername()));
		mv.addObject("_carrello", (Carrello)session.getAttribute("carrello"));
		mv.addObject("utente_log", (Utente)session.getAttribute("utente_log"));
		return mv;
	}
	@RequestMapping(value="/edit",method = RequestMethod.GET)
	public ModelAndView saveUserModify(Utente u, BindingResult r) {
		us.saveUtente(u);
		return new ModelAndView("redirect:/acquisti");
	}
	
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView controlloLogin(
			@RequestParam("username") String user,  
			@RequestParam("password") String pass, HttpSession session) {
		if(!us.controlloLogin(user, pass).isEmpty()){
			Carrello carrello = new Carrello();
			session.setAttribute("carrello", carrello);
			session.setAttribute("utente_log",us.controlloLogin(user, pass).get());	
			return new ModelAndView("redirect:/acquisti");
		} else {
			return new ModelAndView("redirect:/");
		}
	}
	
	/*-----------------Logout utente */
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	public ModelAndView logoutPage(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("logout");
		mv.addObject("utente_log", (Utente)session.getAttribute("utente_log"));
		return mv;
	}
	
	@RequestMapping(value="/logoututente",method = RequestMethod.GET)
	public ModelAndView logoutUtente(HttpSession session) {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	/*-----------------Gestione acquisti */
	@RequestMapping(value="/acquisti",method = RequestMethod.GET)
	public ModelAndView acquisti(HttpSession session){
		if(session.getAttribute("utente_log") == null) 
			return new ModelAndView("redirect:/login");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("acquisti");
		List<Articolo> listaArticoli = as.getAll(); 
		mv.addObject("listaArticoli",listaArticoli);
		mv.addObject("_carrello",(Carrello) session.getAttribute("carrello"));
		mv.addObject("utente_log",(Utente) session.getAttribute("utente_log"));
		return mv;
	}
	
	/*-----------------Gestione carrello */
	@RequestMapping(value="/add/{id}",method = RequestMethod.GET)
	public ModelAndView add(HttpSession session, @PathVariable Long id){
		Articolo a = as.findById(id).get();
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		if(a.getIdArticolo() != 0) {
			carrello.aggiungiArticolo(Long.toString(a.getIdArticolo()), a.getMarca(), 
					a.getModello(), a.getPrezzo());
		}
		return new ModelAndView("redirect:/acquisti");	
	}
	
	@RequestMapping(value="/carrello",method = RequestMethod.GET)
	public ModelAndView carrello(HttpSession session){
		try {
			List<String[]> articoli = converti(session);
			ModelAndView mv = new ModelAndView();
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			if(carrello.getArticoli() == 0)
				return new ModelAndView("redirect:/acquisti");	
			mv.setViewName("carrello");
			mv.addObject("_carrello",(Carrello) session.getAttribute("carrello"));
			mv.addObject("utente_log",(Utente) session.getAttribute("utente_log"));
			mv.addObject("listaArticoli",articoli);
			return mv;
		} catch(NullPointerException exc) {
			return new ModelAndView("redirect:/login");	
		}
	}
	
	private List<String[]> converti(HttpSession session){
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Vector<String[]> articoli = new Vector<String[]>();
		Enumeration<String[]> prodotti = carrello.listaProdotti();
		while(prodotti.hasMoreElements()) {
			String[] prodotto = prodotti.nextElement();
			articoli.add(prodotto);
		}
		return articoli;
	}
	
	@RequestMapping(value="/remove/{id}",method = RequestMethod.GET)
	public ModelAndView add(HttpSession session, @PathVariable String id){
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		carrello.rimuoviArticolo(id);
		return new ModelAndView("redirect:/carrello");	
	}
	
	/*-----------------Checkout */
	@RequestMapping(value="/checkout",method = RequestMethod.GET)
	public ModelAndView salvaOrdine(HttpSession session){
		Carrello carrello = (Carrello) session.getAttribute("carrello");
		Utente utente = (Utente) session.getAttribute("utente_log");
		Ordine ord = new Ordine();
		ord.setTotale(carrello.totaleComplessivo());
		ord.setData(new Date());
		ord.setUtente(utente);
		os.saveOrdine(ord);
		Enumeration<String[]> prodotti = carrello.listaProdotti();
		while(prodotti.hasMoreElements()) {
			String prodotto[] = prodotti.nextElement();
			OrdineArticolo oa = new OrdineArticolo();
			oa.setArticolo(as.findById(Long.parseLong(prodotto[4])).get());
			oa.setOrdine(ord);
			oa.setQta(Integer.parseInt(prodotto[3]));
			oas.saveOrdineArticolo(oa);
		}
		Carrello nCarrello = new Carrello();
		session.setAttribute("carrello", nCarrello);
		return new ModelAndView("redirect:/acquisti");	
	}	
	@RequestMapping(value="/ordini", method = RequestMethod.GET)
	public ModelAndView visualizzaOrdine(HttpSession session) {
		if(session.getAttribute("utente_log")==null)
			return new ModelAndView("redirect:/login");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ordini");
		List<Ordine> ordini = os.getAll();
		mv.addObject("ordini", ordini);
		mv.addObject("utente_log", (Utente) session.getAttribute("utente_log")); //torna l'utente loggato
		return mv;
	}
	
}
