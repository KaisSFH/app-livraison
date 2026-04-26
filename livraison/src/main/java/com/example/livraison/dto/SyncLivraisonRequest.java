package com.example.livraison.dto;

import lombok.Data;
import java.util.List;

@Data
public class SyncLivraisonRequest {
    private List<LivraisonSyncItem> livraisons;

    @Data
    public static class LivraisonSyncItem {
        private Integer nocde;
        private String etatliv;
        private String remarque;
    }
}
