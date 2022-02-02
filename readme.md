## Task 1
I just made a new program where I could test a bit more, and enjoy the process. So I made a player that runs around and shoots AI’s. I did not bother to have any mechanics of resetting or letting the player die, but rather just an infinite shooter of some sorts.

## Task 2
Singleton pattern can be found in Engine.java

## Task 3
I chose to spend most of my time making an ECS, trying to get a better understanding of it, it is probably very inefficient, but I believe I got the understanding of it, and the poor areas can be optimized with sorted HashMaps<Tag, List<Entities>() where a Tag is a grouping of components, Like (SpriteRenderer, Transform), even with this the list of entities may not be in a perfect order in the memory, as the systems like RenderingSystem wants to iterate through only the entities which has got said grouping.

The systems could also cache the entities and its components to not need to fetch them every iteration.

The state pattern is also implemented in the state package, GameStateHandler.java, MainMenuState.java.

However my main focus and goal was just creating something that tested the ECS I made.

## Task 4: Theory
### 4.A) For the patterns listing in Step3, which are architectural patterns, and which are design
patterns? What are the relationships and differences of architectural patterns and design
Patterns?

- Observer - Design Pattern
- State - Design pattern
- Template Method - Design pattern
- Model View Controller - Architectural pattern
- Abstract Factory - Design pattern
- Entity Component System - Architectural pattern
- Pipe and filter - Architectural pattern
Architectural patterns are at planning phase time while design patterns are at building phase time, so when you plan the architecture of the program you can use architectural patterns that will describe the structure of the systems, which could be used as a blueprint. While the design pattern is an actual implementation.

### 4.b) How is the pattern you chose realized in your code? (Which class(es) works as the
pattern you chose?)

- For the ECS pattern, I have a few main classes that describe the rest of the implementations. 
- GameObject - The Entity, which has a list of Components
- Component - An abstract class, which the only attribute it has is which GameObject it belongs to
  - BoxCollider
  - SpriteRenderer
  - Transform
  - Behavior
    - PlayerBehaviour - A kind of quick and dirty manager for the player, could be divided into multiple entity classes
    - BulletBehaviour - Handles the Bullet GameObject
    - EnemyBehaviour - Handles AIs
- EntitySystem - The abstract class that handles initialization, and execution of its components
  - RenderingSystem - Gets all entities with SpriteRenderer and Transform Components and will draw the sprite based on the transform component
  - PhysicsSystem - Gets all the entities with a Collider Component and checks if it overlaps any other entity and sends a message to entities which are colliding and implements CollisionListener
  - BehaviourSystem* - A kind of special system, gets all entities with Behaviour Components and executes those

* A Behavior derives from Component, but has extra abstract methods onStart, onUpdate and dispose, these classes are logic classes opposed to the Component classes which should be pure data classes.

The state pattern is realized with the Abstract class State, and a GameStateHandler which handles which transitions to new states. Main Menu state is the only state in this assignment, as I did not really focus on completing it, but rather learning ECS, and focusing most of my time on that.

### 4.c) Is there any advantages in using this pattern in this program? (What are the
advantages/disadvantages?)
In this assignment, the enemy and player are such simple objects that a full blown ECS pattern is most likely an overkill, however it would simplify extending the game if I wanted more enemies with different attributes, as much time as I spent on the ECS implementation, adding new objects turned out to be very simple.

The biggest advantage of an ECS is having wide hierarchies rather than deep, which turns into a inheritance and polymorphism hell. As such Extensibility and Modifiability is a huge advantage. The downside is that it can be quite time consuming implementing, especially from scratch, and in a language where you don’t have complete control of memory, which can end up hurting performance.
In a simple game like this with a quick update loop, the game would probably perform better.

In theory it should be “easy” to multi thread an ECS, but mine probably isn't :’)

Also should probably be implemented in a language where you get complete control of memory access, so that all components are sorted next to eachother in the memory, so that iterating components goes as fast as possible. Also garbage collection.

An disadvantage is that ECS is a less defined pattern, so it is easy to misuse components/systems, which is not unlikely that I have done, this you think a lot about design, which can consume a lot of time.
