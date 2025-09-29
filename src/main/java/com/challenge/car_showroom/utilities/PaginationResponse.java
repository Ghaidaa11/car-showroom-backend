package com.challenge.car_showroom.utilities;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationResponse<T> {

    private List<T> data;
    private Map<String, Object> meta;

    public PaginationResponse(Page<T> page) {

        this.data = page.getContent();
        this.meta = new HashMap<String, Object>();

        this.meta.put("page", page.getNumber() + 1);
        this.meta.put("total_elements", page.getTotalElements());
        this.meta.put("total_pages", page.getTotalPages());
        this.meta.put("last_page", page.isLast());
    }

    public List<T> getData() {
        return data;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }
}
