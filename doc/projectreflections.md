# Project Reflections

This file outlines Team Rockets' experiences with the project. As such, since there are four members in our team, we'll divide the document into four sections so that each member can outline and elaborate on their personal experiences with the project.

## Xavier

I've had an interesting time with this project doing many different tasks, so I'll break them into sub-sections and then elaborate on my experiences in those sub-sections.

### Assorted Tools

One of the key tasks I did at the beginning of the project was setting up and debugging the Gradle build system we use and the cucumber testing platform we use. I previously didn't have much experience using either of those tools, so implementing and debugging that implementation gave me a lot of good experience with those tools. I also acquired some useful experience with other plugins and tools in IntelliJ, like the Profiling and Diagramming tools. Finally, I further increased my knowledge of GitHub and some of its integrated tools, like GitHub actions and how you can interface with those to create branch rules and merge restrictions.

### Project Management

I've done a lot of background administration and organization in this project. For example, I often plan out the meeting topics and lead the team through the meetings as well. While this has taken a good amount of time outside of the normal development work that I do, it's been rewarding, and I feel like it's given me some real-world experience on what it's like to take a leadership role for a small team of developers. I also learned how you can use chat and project organization software to organize meetings and work in a way that's easy to follow and understand.

### Development

Throughout this project, I feel like I've been improving my general development skills, whether by learning about different tools or things like Object Oriented Design Patterns. I've also learned more about writing proper tests and ensuring that when you write code, it's expandable and highly readable so that we can lower the amount of effort required for maintenance or new features. Of course, there can be problems caused by trying to overcomplicate things, and as such, I think that will likely be the next area where I improve my own ability.

### Documentation

One of the things I've been working on throughout this project is documentation, and it's really opened my eyes to how much documentation can be necessary for a project. On some previous projects I'd worked on, the amount of non-code documentation was very minimal, and looking back, that was a tremendous error that likely caused some of the issues I experienced in those projects later on. As such, having to write out all of these different documentation files and working with markdown has given me a newfound appreciation for writing good, clean, and readable documentation that goes over the intricacies of the project it's attached to. 

### Conclusion

I've worked with various tools and techniques throughout this project, which has allowed me to confront the different problems in the project in interesting ways. For example, when constructing the Terminal Flags module, I was constantly referring back to the chain of responsibility pattern so that it would be easily expandable when we needed more flags. This extra work paid off when I needed to implement time-related flags for some simulation parameters later. As such, I've gained a healthy appreciation for how proper planning can make a project's development smoother and easier to do.

## Dale

### Tools

I've worked with several tools over the course of this project that I'm already familiar with including git, GitHub, 
and IntelliJ. I've learned more about GitHub, however, than I previously knew about it. I've become familiar with 
GitHub actions, projects, and issues. These are all invaluable tools that help with the software development process, 
especially in a team-based setting.

I've also become familiar with some new tools that I've never used before. Cucumber is an intuitive code testing tool 
that allows people with little programming knowledge to write tests in Gherkin which developers can then implement in 
the language of their choice. Learning how to write effective tests in Cucumber has broadened my understanding of the 
process of testing code.

One last thing that I'd like to say about my experience with tools in this project is that I've discovered that I don't 
particularly like coding in Java. I find the requirement of using classes for every piece of code to be extremely 
cumbersome. For my future projects, I'd like to try using different languages, preferably ones with less strict 
object-orientation if they are even object-oriented at all.

### Development

I've learned several new concepts related to software development that have improved my understanding of common 
development paradigms. I've learned about and used common design patterns including factories and singletons. I've also 
practiced software engineering principles including SOLID principles and agile principles. These have been instrumental 
to my development as a software engineer.

### Documentation

While I've never minded writing documentation as much as others, the sheer amount of documentation for this project is 
on another level compared to what I'm used to. I actually have discovered that I like this much focus on documentation 
though. Requiring developers to not only write documentation for everything but to also write high-quality 
documentation makes it much easier to return to a piece of code after having left it untouched for a long time. I've 
also found myself trying to write highly readable code instead of hacking something together for a temporary solution. 
This helps the entire team work together on various pieces of the project throughout the duration of the project.

## Jonathan

### Tools 

During this project I got to work with a lot of tools that previously I had no experience with. The two primary tools being gradle and cucumber. Gradle was used for the build system and was useful experience with manually building and running the project. Cucumber helped me to get used to using a different testing framework from what I am used to using (JUnit) and see some of the advantages/disadvantages of other testing frameworks. This also served as valuable experience in using Git, as I have never really used it properly and the many tools it offers up until now. Specifically using branches, resolving merge conflicts, merge restrictions, and managing pull requests.  

### Development 

The bulk of my work throughout this project has been on the simulation class, and the UI class. These two classes handle a lot of the logic for the different features present in the project. In working on this, I've learned a lot more about the importance of design patterns, documentation, and well defined features. This became particularly apparent to me as I frequently had to consult documentation, review code, or reference materials written by others while working on feature enhancements or bug fixes. Working on the Simulation class, taught me the value of design patterns in creating scalable and maintainable code. The importance of design patterns was more and more evident as we began implementing more and more features, and how those design patterns could help us to avoid a lot of issues when scaling the project. 

### Testing 

Test driven development was something that was emphasized during the project and it really helped to avoid a lot of the issues that we started to run into at the beginning of the project. As we focused more on testing with the implementation of cucumber, we saw less issues. After we had the unit testing process down, I did some research and came up with an Integration testing process. The integration testing was new to me, as I have only ever dealt with unit tests. This all gave me a much better perspective on the importance of testing. 

### Documentation 

Although the documentation at times was very tedious, the documentation that we developed proved to be very helpful as something to refer back to. The documentation proved to be helpful in nearly every aspect of the project, whether that was implementing a new feature, discussing meeting topics, or planning future sprints. Keeping documentation for nearly eveyrthing helped in the long term for the project.

## Ryan

### Tools 

During this project, I had the chance to work with a variety of tools, some I was already familiar with and some that were totally new to me. IntelliJ and git were two tools we used that I am already familiar with through both school and work, but it was good to gain more experience with both. Cucumber was the testing framework we used. I found it a little difficult to work with as tests became more complex, but it was still good to learn something new.

### Development 

My development work focused on organisms and their associated classes and methods. This work involved a lot of object-oriented programming that highlighted the importance of clearly defining features and communicating with the team during development. With so many classes connected to each other, it became very easy for a change in one organism to impact the whole ecosystem. Documentation was also very helpful as development proceeded as it allowed for quick understanding of other team members’ work as well as pieces of code that I had written but hadn’t looked at in a while.

### Testing 

The project was a good experience with test-driven development. It was very helpful to think about what a feature was supposed to do and create tests for it as part of the development process. Requiring unit testing for every feature also helped keep our project working well and ensured that tests were in place to alert us if a new change caused a previous feature to break, which did happen a few times as I made modifications to the organism classes.

### Documentation 

Writing good documentation was very helpful throughout the project. Although it seemed annoying at first, writing documentation turned out to be very easy, and its benefits far outweighed the little bit of extra time it took to write. Having good documentation helped with every aspect of the project whether it was exploring a feature that someone else had worked on or trying to understand a method I had written a couple weeks ago.
