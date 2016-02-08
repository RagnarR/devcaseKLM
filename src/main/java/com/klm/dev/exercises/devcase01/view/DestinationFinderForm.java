package com.klm.dev.exercises.devcase01.view;

import java.util.List;

public class DestinationFinderForm {

    private String origin;
    private String pos;
    private Double minBudget;
    private Double maxBudget;
    private Boolean sortByPrice = Boolean.FALSE;
    private Integer page;
    private List<Integer> pageIndexes;

    // This check can/should be done with a Validator
    public boolean isValid() {
        //@formatter:off
        return getOrigin() != null && !getOrigin().isEmpty()
                && getPos() != null && !getPos().isEmpty()
				/*&& getMinBudget() != null && !getMinBudget().isNaN()*/
                && getMaxBudget() != null && !getMaxBudget().isNaN();
        //@formatter:on
    }

    /**
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * @param origin the origin to set
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * @return the pos
     */
    public String getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(String pos) {
        this.pos = pos;
    }

    /**
     * @return the minBudget
     */
    public Double getMinBudget() {
        return minBudget;
    }

    /**
     * @param minBudget the minBudget to set
     */
    public void setMinBudget(Double minBudget) {
        this.minBudget = minBudget;
    }

    /**
     * @return the maxBudget
     */
    public Double getMaxBudget() {
        return maxBudget;
    }

    /**
     * @param maxBudget the maxBudget to set
     */
    public void setMaxBudget(Double maxBudget) {
        this.maxBudget = maxBudget;
    }

    /**
     * @return the sortByPrice
     */
    public Boolean getSortByPrice() {
        return sortByPrice;
    }

    /**
     * @param sortByPrice the sortByPrice to set
     */
    public void setSortByPrice(Boolean sortByPrice) {
        this.sortByPrice = sortByPrice;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the pageIndexes
     */
    public List<Integer> getPageIndexes() {
        return pageIndexes;
    }

    /**
     * @param pageIndexes the pageIndexes to set
     */
    public void setPageIndexes(List<Integer> pageIndexes) {
        this.pageIndexes = pageIndexes;
    }

}
