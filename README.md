# Reactive Programming (Currently Studying)

- Problems without Reactive Programming:
  * 300 threads in the pool -> 1500 requests per second (suppose: Request per second: 5 - 200ms each)
  * Each thread is responsible for each request
  * Too many request could result in lost of responsivity, and then unavailability
  * If you increase the number of threads, the memory will increase too much (too much cost).
  
- How can it get better?
  * Need Elasticity.
  * Ways:
    1) Light Weight Threads (Coroutines). Ex: Go, Kotlin
    2) Reactive in an Interservices level (using a broker - messaging)
    3) Reactive in a Service level (Reactive Programming)
   
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
  * Schedulers: It is an abstraction to facilitate everything that is low level threading. So, instead of you working at a low level getting a thread and sending a message to other one, block, contention, etc., the framework will make available the scheduler and you do it with a unique line in the code.
  * Observable Pattern: The libs implements the 
  * Pull vs Push ( + Backpressure): Backpressure: The producer can answers "at this moment I can process X messages"
  * The libs in Reactive Program abstract all the complexity with Threads, Concurrency, Locks, Latches, Semaphore, etc.
  * The libs make the code as simple as possible and you can focus on the business.
  * Better latency and throughput compared with the traditional Thread-Per-Request

- Blocking I/O (Conventional method)
  * Code:
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/blocking%20io.jpg?raw=true)
  * Result:
    - Observations: 
      * Thread 1 will be available only when everything is finished. (in Java, each Thread represents at least 1MB. So, if you increase the number of threads, it will cost too much in memory and the CPU will be in idle)
      * Code is simple but is not efficient.
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/blocking%20io%20-%20result.jpg?raw=true)

- Async - Blocking I/O (This model is asynchronous, however, it is still blocking)
  * Code:
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/async%20-%20blocking%20io.jpg?raw=true)
  * Result:
    - Observations:
      * This way, you gain time. You can work parallelly. However, the main thread will still wait for the other two threads to finish and you will work with 3 threads, so: more memory, more cost. 
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/async%20-%20blocking%20io%20-%20result.jpg?raw=true)

- Reactive - Non Blocking I/O (Reactive Programming): The effective way. This example is using WebFlux from Spring.
  * Code: 
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/reactive%20-%20non%20blocking%20io.jpg?raw=true)
  * Result:
    - Observations:
      * The Thread 1 is the Event-loop thread. It will never be blocked. It will keep receiving requests. It will send the processment to other threads, then get the answer from all the threads and respond to the requester.
    - ![](https://github.com/fabiolnh/reactive-programming/blob/main/assets/reactive%20-%20non%20blocking%20io%20-%20result.jpg?raw=true)
