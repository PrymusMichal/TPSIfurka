/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wizut.tpsi.ogloszenia.controllers;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wizut.tpsi.ogloszenia.jpa.BodyStyle;
import wizut.tpsi.ogloszenia.jpa.CarManufacturer;
import wizut.tpsi.ogloszenia.jpa.CarModel;
import wizut.tpsi.ogloszenia.jpa.FuelType;
import wizut.tpsi.ogloszenia.jpa.Offer;
import wizut.tpsi.ogloszenia.services.OffersService;
import wizut.tpsi.ogloszenia.web.OfferFilter;

/**
 *
 * @author pm41486
 */
@Controller
public class HomeController {

    @Autowired
    OffersService _offersService;

    @RequestMapping("/home1")
    public String home1(Model model) {
        try {
            model.addAttribute("manufacturer", _offersService.getCarManufacturer(2));
            model.addAttribute("carModel", _offersService.getModel(3));
        } catch (Exception e) {
            return "home";
        }
        return "home";
    }

    @GetMapping("/home2")
    public String home2(Model model) {
        List<CarManufacturer> carManufacturers = _offersService.getCarManufacturers();
        List<BodyStyle> bodyStyles = _offersService.getBodyStyles();
        List<FuelType> fuelTypes = _offersService.getFuelTypes();
        List<CarModel> carModels = _offersService.getCarModels();
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("carModels", carModels);
        return "offersList";
    }

    @GetMapping("/")
    public String home(Model model, OfferFilter offerFilter) {
        List<CarManufacturer> carManufacturers = _offersService.getCarManufacturers();
        List<CarModel> carModels = _offersService.getCarModels();

        List<Offer> offers;
        if (offerFilter.getManufacturerId() != null) {
            offers = _offersService.getOffersByManufacturer(offerFilter.getManufacturerId());
        } else {
            offers = _offersService.getOffers();
        }
        model.addAttribute("carManufacturers", carManufacturers);
        model.addAttribute("carModels", carModels);
        model.addAttribute("offers", offers);

        return "offersList";
    }

    @GetMapping("/offer/{id}")
    public String offerDetails(Model model, @PathVariable("id") Integer id) {
        Offer offer = _offersService.getOffer(id);
        model.addAttribute("offer", offer);
        return "offerDetails";
    }

    @GetMapping("/newoffer")
    public String newOfferForm(Model model, Offer offer) {
        List<CarModel> carModels = _offersService.getCarModels();
        List<BodyStyle> bodyStyles = _offersService.getBodyStyles();
        List<FuelType> fuelTypes = _offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        model.addAttribute("header", "Nowe ogłoszenie");
        model.addAttribute("action", "/newoffer");
        return "offerForm";
    }

    @PostMapping("/newoffer")
    public String saveNewOffer(Model model, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            List<CarModel> carModels = _offersService.getCarModels();
            List<BodyStyle> bodyStyles = _offersService.getBodyStyles();
            List<FuelType> fuelTypes = _offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);
            model.addAttribute("header", "Nowe ogłoszenie");
            model.addAttribute("action", "/newoffer");

            return "offerForm";
        }
        offer = _offersService.createOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }

    @GetMapping("/deleteoffer/{id}")
    public String deleteOffer(Model model, @PathVariable("id") Integer id) {
        Offer offer = _offersService.deleteOffer(id);

        model.addAttribute("offer", offer);
        return "deleteOffer";
    }

    @GetMapping("/editoffer/{id}")
    public String editOffer(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("header", "Edycja ogłoszenia");
        model.addAttribute("action", "/editoffer/" + id);
        Offer offer = _offersService.getOffer(id);
        model.addAttribute("offer", offer);
        List<CarModel> carModels = _offersService.getCarModels();
        List<BodyStyle> bodyStyles = _offersService.getBodyStyles();
        List<FuelType> fuelTypes = _offersService.getFuelTypes();

        model.addAttribute("carModels", carModels);
        model.addAttribute("bodyStyles", bodyStyles);
        model.addAttribute("fuelTypes", fuelTypes);
        return "offerForm";
    }

    @PostMapping("/editoffer/{id}")
    public String saveEditedOffer(Model model, @PathVariable("id") Integer id, @Valid Offer offer, BindingResult binding) {
        if (binding.hasErrors()) {
            model.addAttribute("header", "Edycja ogłoszenia");
            model.addAttribute("action", "/editoffer/" + id);

            List<CarModel> carModels = _offersService.getCarModels();
            List<BodyStyle> bodyStyles = _offersService.getBodyStyles();
            List<FuelType> fuelTypes = _offersService.getFuelTypes();

            model.addAttribute("carModels", carModels);
            model.addAttribute("bodyStyles", bodyStyles);
            model.addAttribute("fuelTypes", fuelTypes);

            return "offerForm";
        }
        _offersService.saveOffer(offer);

        return "redirect:/offer/" + offer.getId();
    }
}
