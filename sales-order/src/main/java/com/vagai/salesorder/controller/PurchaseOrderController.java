package com.vagai.salesorder.controller;

import com.vagai.salesorder.dto.order.PurchaseOrderDto;
import com.vagai.salesorder.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1", produces = APPLICATION_JSON_VALUE)
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @GetMapping("/purchase-order/{purchaseOrderId}")
    public ResponseEntity<PurchaseOrderDto> getPurchaseOrderById(@PathVariable Long purchaseOrderId) {
        return ok(purchaseOrderService.getPurchaseOrderById(purchaseOrderId));
    }

    @PostMapping("/purchase-order")
    public ResponseEntity<PurchaseOrderDto> createPurchaseOrder(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return ok(purchaseOrderService.createPurchaseOrder(purchaseOrderDto));
    }

    @PostMapping("/purchase-order-fixing-n-plus-one")
    public ResponseEntity<PurchaseOrderDto> createPurchaseOrderFixingNPlusOneRestCall(@RequestBody PurchaseOrderDto purchaseOrderDto) {
        return ok(purchaseOrderService.createPurchaseOrderFixingNPlusOneRestCall(purchaseOrderDto));
    }

    @GetMapping("/purchase-orders")
    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrderByClientId(@RequestParam("clientId") Long clientId) {
        return ok(purchaseOrderService.getPurchaseOrderByClientId(clientId));
    }

    @GetMapping("/purchase-orders-fixing-n-plus-one")
    public ResponseEntity<List<PurchaseOrderDto>> getPurchaseOrderByClientIdFixingNPlusOne(@RequestParam("clientId") Long clientId) {
        return ok(purchaseOrderService.getPurchaseOrderByClientIdFixingNPlusOne(clientId));
    }
}
