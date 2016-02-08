package com.klm.dev.exercises.devcase01;

import com.klm.dev.exercises.devcase01.bo.ApplicationInfoManager;
import com.klm.dev.exercises.devcase01.model.Route;
import com.klm.dev.exercises.devcase01.service.DestinationService;
import com.klm.dev.exercises.devcase01.view.DestinationFinderForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ApplicationInfoManager applicationInfoManager;

    @RequestMapping(value = { "/", "/index.html" }, method = GET)
    public String redirectToWebUiIndex() {
        return "redirect:/web-ui/destination-finder.html";
    }

    @RequestMapping(value = "assignment.html", method = GET)
    public String redirectToWebUiAssignment() {
        return "redirect:/web-ui/destination-finder.html/#assignment";
    }

    @RequestMapping(value = "/find-destination")
    public Callable<String> findDestination(@ModelAttribute("destination-finder-params") final DestinationFinderForm form, final ModelMap model,
                                  final HttpSession session) {

        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                LOGGER.debug("findDestination: START");

                long startTime = System.nanoTime();

                @SuppressWarnings("unchecked")
                PagedListHolder<Route> paginatedDestinations = (PagedListHolder<Route>) session.getAttribute("paginatedDestinations");

                if (isLoadDestinations(form, paginatedDestinations)) {
                    List<Route> routes = findRoutes(form);

                    LOGGER.debug("findDestination: destinations: {}", routes);

                    paginatedDestinations = setupPaginatedDestination(model, routes);
                }

                selectPage(form.getPage(), paginatedDestinations);

                addPageIndexesToModel(model, paginatedDestinations);

                session.setAttribute("paginatedDestinations", paginatedDestinations);

                updateStatistics(startTime);

                LOGGER.debug("findDestination: END");
                return "destination-finder";
            }
        };
    }

    private void updateStatistics(long startTime) {
        long endTime = System.nanoTime();

        applicationInfoManager.updateStatistics(endTime - startTime);

        try {
            applicationInfoManager.storeApplicationInfo();
        } catch (Throwable e) {
            LOGGER.warn("Error while storing Application Information");
        }
    }

    private void addPageIndexesToModel(ModelMap model, PagedListHolder<Route> paginatedDestinations) {
        List<Integer> pageIndexes = initPageIndexes(paginatedDestinations);

        model.addAttribute("pageIndexes", pageIndexes);
    }

    private PagedListHolder<Route> setupPaginatedDestination(ModelMap model, List<Route> routes) {

        if (routes != null) {
            PagedListHolder<Route> paginatedRoutes = initPaginatedDestinations(routes);
            model.addAttribute("paginatedDestinations", paginatedRoutes);
            return paginatedRoutes;
        }

        return null;
    }

    private List<Route> findRoutes(DestinationFinderForm form) {

        Future<List<Route>> routes = null;
        List<Route> out = null;

        if (form.isValid()) {
            try {
                routes = destinationService.retrieveDestinations(form);
                out = routes.get();

            } catch (Throwable t) {
                LOGGER.info("Error while retrieving routes list.", t);
            }
        }
        return out;
    }

    private boolean isLoadDestinations(DestinationFinderForm form, PagedListHolder<Route> paginatedDestinations) {
        return paginatedDestinations == null || form.isValid();
    }

    private List<Integer> initPageIndexes(PagedListHolder<Route> paginatedDestinations) {
        List<Integer> pageIndexes = new ArrayList<>();

        if (paginatedDestinations != null) {
            for (int i = 0; i < paginatedDestinations.getPageCount(); i++) {
                pageIndexes.add(i);
            }
        }

        return pageIndexes;
    }

    private void selectPage(Integer pageNumber, PagedListHolder<Route> paginatedDestinations) {
        if (pageNumber != null && paginatedDestinations != null) {

            paginatedDestinations.setPage(pageNumber);
        }
    }

    private PagedListHolder<Route> initPaginatedDestinations(List<Route> routes) {
        PagedListHolder<Route> paginatedDestinations = new PagedListHolder<>(routes);

        paginatedDestinations.setPageSize(10);
        return paginatedDestinations;
    }

}
