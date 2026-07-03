package org.alticelabs.analytics.control;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.alticelabs.cart.control.CartRepository;
import org.alticelabs.cart.entity.Cart;
import org.alticelabs.cartItem.entity.CartItem;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnalyticsService {

    @Inject
    CartRepository repository;

    public long cartsWithItems() {
        return repository.listAll().stream()
                .filter(cart -> cart.getItems() != null && !cart.getItems().isEmpty())
                .count();
    }

    public int maxItems() {
        return repository.listAll().stream()
                .mapToInt(cart -> cart.getItems() == null ? 0 : cart.getItems().size())
                .max()
                .orElse(0);
    }

    public int minItems() {
        return repository.listAll().stream()
                .filter(cart -> cart.getItems() != null && !cart.getItems().isEmpty())
                .mapToInt(cart -> cart.getItems().size())
                .min()
                .orElse(0);
    }

    public double averageItems() {
        return repository.listAll().stream()
                .mapToInt(cart -> cart.getItems() == null ? 0 : cart.getItems().size())
                .average()
                .orElse(0);
    }

    public Map<UUID, Integer> topItems() {

        return repository.listAll().stream()

                .map(Cart::getItems)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .filter(Objects::nonNull)

                .collect(Collectors.groupingBy(
                        CartItem::getProductId,
                        Collectors.summingInt(CartItem::getQuantity)
                ))

                .entrySet().stream()
                .sorted(Map.Entry.<UUID, Integer>comparingByValue().reversed())
                .limit(5)

                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
