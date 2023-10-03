# Design Patterns

This file is intended to show which design patterns we're currently using, which patterns may be helpful, and how we 
plan to continue designing modules.
## Patterns Currently in Use

We are currently using the factory pattern to assist with AbstractOrganism creation. It allows us to simply register a string and an instance of an object to the factory and then produce more of them by calling a method with that same string.
We also use the singleton for our factory so there's only one factory at a time. The final pattern we're currently using is the Chain of Responsibility which is used for our Terminal Flag Handlers.

## Possibly Helpful Patterns

Some patterns that may be helpful are:
* Singleton - The singleton is a design pattern that specifically ensures that no more then one object of the class 
exists. It also passes that object back when a client attempts to create a new object.
* Chain of Responsibility - Lets the client interact with a handler rather then directly processing requests. Requests 
are passed between handlers until one decides to handle the request.
* Builder - A director specifies an interface which calls a specific builder to produce a product object. This gives 
the director more control over the product via a builder interface. 

## Module Design Plans

During our module design process we'll be referring to proper pattern design and java references. We'll also take advantage of class and sequence diagrams to plan behavior before it's being implemented. Furthermore, we're implementing test-driven development into our processes.

