# GymportalService
## Service Oriented Software Engineering - Final Test

Although you are free to choose the application domain and the purpose of the application, some constraints follow:
1. The application must be composed of both REST services and SOAP services (developed by using Apache CXF).
2. The application must comprise (at least) three service providers, (at least) two service prosumers, and (at least) one client application (e.g., Java application, Web application – see next point).
3. One Android mobile app (possibly very basic, similarly to the one shown during the seminar lecture by Gian Luca Scoccia) must be implemented to show some interactions with the service side. The mobile app does not need to be able to handle all of the possible interactions, unless you opt for implementing solely the Android mobile app (without realizing neither a Java application nor a Web application – see previous point): in this case the mobile app must be “complete enough” so to be used to demonstrate all of the possible interactions with the server side.
4. The realization/simulation” of (at least one) microservice(s) (as per the seminar lecture by Vaidhyanathan) and the employment of a (possibly simulated) load balancer (as per the seminar lecture by Filippone) would be greatly appreciate and rewarded.
5. The client(s) must interact with (at least) the prosumer(s), and the prosumer(s) must interact with (at least) two providers (e.g., for aggregating data from them).
6. Concerning the three providers, at least one of them must be already exiting (e.g., Google, Amazon, …), at least one of them must be implemented by you from scratch.
7. Develop at least one asynchronous Web Service (polling and/or callback approaches) and clearly motivate needs and tangibly show advantages.
8. At least two prosumers must execute their job “in parallel” and then synchronize/coordinate each other before responding to the client(s), e.g., run in parallel for collecting data, then synchronize/coordinate each other for polishing/aggregating the collected data, and only after respond to the client(s) asynchronously.
9. Concerning the client(s), the goal is to show at least three different interactions with the prosumer(s). In addition to the interactions with the prosumer(s), the client(s) can also interact with the providers directly, if needed.
10. From an engineering point of view, you must realize a software architecture diagram by using a service/component diagram or the basic blocks-lines diagram (blocks for services/components and lines for connections). Still, the interaction scenarios must be shown and documented. For each interaction scenario, the corresponding sequence diagrams must be produced.
11. The application, its goals, expected outputs, the way it works, user and/or developer guide, etc., must be introduced with a clear textual description (a .docx or .pptx would be ok).
12. The code must be documented with clear and verbose comments within the source code, WSDL files, etc. Use Open API and Swagger for all REST services.
13. A short README.txt file must be provided to clearly and unambiguously explain all the steps to be followed to set up the application.
14. Use ECLIPSE and ANDROID STUDIO as development environments.
15. The project(s) must be Mavenized.
16. [Optionally] Maven archetype(s) can be realized.
    
OPTIONAL ELEMENTS
The following elements are more than welcome and will contribute to your final grade:
- Maven archetype
- WSDL 2.0 Message Exchange Patterns
- WS-Addressing
- WS-Reliable Messaging
- WS-Discovery
- WS-Policy
- WS-Security