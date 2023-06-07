package com.vagai.salesorder.service;

import com.vagai.salesorder.caller.ProductCaller;
import com.vagai.salesorder.dto.order.PurchaseOrderDto;
import com.vagai.salesorder.entity.PurchaseOrder;
import com.vagai.salesorder.exception.PurchaseOrderNotFoundException;
import com.vagai.salesorder.mapper.PurchaseOrderMapper;
import com.vagai.salesorder.mapper.ReserveProductDtoMapper;
import com.vagai.salesorder.repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;
    private final ProductCaller stockProductCaller;
    private final ReserveProductDtoMapper reserveProductDtoMapper;

    @Transactional
    public PurchaseOrderDto createPurchaseOrder(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(purchaseOrderMapper.mapToPurchaseOrder(purchaseOrderDto));

        purchaseOrder.getPurchaseOrderItems()
                .stream()
                .map(reserveProductDtoMapper::mapToReserveProductDto)
                .forEach(stockProductCaller::reserveProductItem);

        return purchaseOrderMapper.mapToPurchaseOrderDto(purchaseOrder);
    }

    @Transactional
    public PurchaseOrderDto createPurchaseOrderFixingNPlusOneRestCall(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = purchaseOrderRepository.save(purchaseOrderMapper.mapToPurchaseOrderFixNPlusOneRestCall(purchaseOrderDto));

        purchaseOrder.getPurchaseOrderItems()
                .stream()
                .map(reserveProductDtoMapper::mapToReserveProductDto)
                .collect(Collectors.collectingAndThen(toList(), stockProductCaller::reserveProductItemFixingNPlusOne));

        return purchaseOrderMapper.mapToPurchaseOrderDto(purchaseOrder);
    }

    public List<PurchaseOrderDto> getPurchaseOrderByClientId(Long clientId) {
        return purchaseOrderRepository.findByClientId(clientId)
                .stream()
                .map(purchaseOrderMapper::mapToPurchaseOrderDto)
                .collect(toList());
    }

    public List<PurchaseOrderDto> getPurchaseOrderByClientIdFixingNPlusOne(Long clientId) {
        return purchaseOrderRepository.findByClientIdWithItems(clientId)
                .stream()
                .map(purchaseOrderMapper::mapToPurchaseOrderDto)
                .collect(toList());
    }

    public PurchaseOrderDto getPurchaseOrderById(Long purchaseOrderId) {
        return purchaseOrderRepository.findById(purchaseOrderId)
                .map(purchaseOrderMapper::mapToPurchaseOrderDto)
                .orElseThrow(() -> new PurchaseOrderNotFoundException(purchaseOrderId));
    }
}
