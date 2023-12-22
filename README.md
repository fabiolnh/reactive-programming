# Reactive Programming

- Problems without Reactive Programming:
  * 300 threads in the pool -> 1500 requests per second (suppose: Request per second: 5 - 200ms each)
  * Each thread is responsible for each request
  * Too many request could result in lost of responsivity, and them unavailability
  * If you increase the number of threads, the memory will increase too much (too much cost).
  
- How can it get better?
  * Need Elasticity.
  * Ways:
    1) Light Weight Threads (Coroutines). Ex: Go, Kotlin
    2) Reactive in an Interservices level (using a broker - messaging)
    3) Reactive in a Service level (reactive programming)
   
- Observations
  * Reactive Program is not a paradigm (functional, structural, object oriented)
  * It can be an asynchronous programming model where the flux is composed of observables and event reactions without too much sacrifice. (the frameworks can work with a lot of thread, send messages between them, without sacrifice)
  * It it divided in two fields:
    1) Event-loop: Is the key to work. Everything works because of it. These examples implement the Event-Loop (IO Reactor, Netty, AsyncHttp)
    2) Reactive-extensions: A specification created for libs to implement the Reactive Programming (RxJava, Reactor, Mutiny)

- Characteristics of Reactive Programing
  * Publishers: (Interface. The father one. The rest is below it)
     - Multi (Can publish: 0, N or Error events): Flux, Mono
     - Single (Can publish: 0, 1 or Error events): Observable, Single
  * Lazy Evaluation: Nothing happens until someone subscribes in an observable font. If no one subscribes, it will not execute.
  * Publisher Categories:
     - Hot: (Example in real life: you arrive late at the cinema but the movie is in the middle, you cannot go back)
     - Cold: (Example in real life: you choose a movie in netflix and you can go back and forward and watch the time that you want)
  * Schedulers: It is an abstraction to facilitate everything that is low level threading. So, instead of you work in a low level getting a thread and sending a message to other one, block, contention, etc., the framework will make available the scheduler and you do it with a unique line in the code.
  * Observable Pattern: The libs implements the 
  * Pull vs Push ( + Backpressure): Backpressure: The producer can answers "at this moment I can process X messages"
  * The libs in Reactive Program abstract all the complexity with Threads, Concurrency, Locks, Latches, Semaphore, etc.
  * The libs make the code as simple as possible and you can focus on the business.
  * Better latency and throughput compared with the traditional Thread-Per-Request
