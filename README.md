# Reactive Programming

- Problems without Reactive Programming:
  * 300 threads in the pool -> 1500 requests per second (suppose: Request per second: 5 - 200ms each)
  * Each thread is responsible for each request
  * Too many request could result in lost of responsivity, and them unavailability
  * If you increase the number of threads, the memory will increase too much (too much cost).
  
- How can get it better?
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

- Features of Reacture Programing
  * 
