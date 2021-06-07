/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
// tag::code[]
package com.kareem.springreactive.reactivemongo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;

    public HomeController(ItemRepository itemRepository, CartRepository cartRepository) {
        this.itemRepository = itemRepository;
        this.cartRepository = cartRepository;
    }

    @GetMapping
    Mono<Rendering> home() {
        return Mono.just(Rendering.view("home.html")
                .modelAttribute("items"
                        , this.itemRepository.findAll())
                .modelAttribute("cart"
                        , this.cartRepository.findById("My Cart")
                                .defaultIfEmpty(new Cart("My Cart")))
                .build());
    }
}
// end::code[]