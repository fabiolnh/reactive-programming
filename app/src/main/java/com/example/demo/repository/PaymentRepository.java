package com.example.demo.repository;

import com.example.demo.enums.PaymentStatusEnum;
import com.example.demo.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentRepository {

    private final Database database;

    // If you want, you can create your own Scheduler with the number of threads to be available for its poll:
    private static final ThreadFactory THREAD_FACTORY = new CustomizableThreadFactory("database-");
    private static final Scheduler DB_SCHEDULER = Schedulers.fromExecutor(Executors.newFixedThreadPool(8, THREAD_FACTORY)); // Just change the "subscribeOn" to this scheduler

    // OBS: Schedulers is an abstraction of a poll of threads (is the worker).

    //Schedulers.parallel(); // is a Scheduler that is done to work with tasks that uses CPU.
    //Schedulers.boundedElastic(); // is a Scheduler that is done to work IO, so, everything that is blocking, use it. (Ex: RDMS Database, Disk). OBS: If you are using too much this, it is not worth, because too much threads is cost.
    //Schedulers.immediate(); //
    //Schedulers.single(); //


    public Mono<Payment> createPayment(final String userId) {
        final Payment payment = Payment.builder()
                .id(UUID.randomUUID().toString())
                .userId(userId)
                .status(PaymentStatusEnum.PENDING)
                .build();

        // OBS: It will be executed only when someone subscribes to it.
        return Mono.fromCallable(() -> {
                log.info("saving  payment transaction for user");
                return this.database.save(userId, payment);
            })
                .subscribeOn(Schedulers.parallel()) // Selecting the correct pool of threads (it is not the subscribing). If you do not do this, you will be on the Threads of the Event Loop of Netty
                .doOnNext(next -> log.info("Payment received"));
    }

}
