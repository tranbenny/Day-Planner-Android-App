TODO:

Navigation:
- Create a full height navigation drawer for switching between agenda, group messages, user preferences
    - implement navigation bar with icon click and slide, DONE
    - load pages on click, DONE
    - include navigation toolbar/ button click on every screen except start screen, DONE
    - choose relevant navigation drawer icons
    - seperate into my plans and my groups, DONE 
    - figure out a way to navigate into plan generator button pages/actual plans, DONE
    - dynamically add new navigation drawer items onto navigation drawer <-- load types of plans into 
        singleton class, and then programatically set the navigation drawer items 

Base Features:

- Create a group page:
    - add users, group chat with users

- Agenda Page: display time/things to do on a screen

    - computer generated plan and allow for custom user plan
        - include error message when there is no network connection
        - create an event listener so that plan cannot be loaded until all async tasks finish

    - get multiple API calls for food/different things to do
        - yelp-android api does not find results with terms other than "food", "restaurant"
            test this issue with the other apis, DONE
        - create an async task test for calls, DONE
        - load business info into a model, DONE
        - load names into a dictionary, DONE
        - configure categories into async task
        

    - create a prototype plan on the first screen
        - list of pictures with background of restaurant, with text of time interval
            and name of business under it, DONE
        - change font, try roboto font, otherwise import custom type face
        - cache all the background images, DONE
        - create categories for background images
        - add option to delete time blocks, DONE
            - when button is pressed, the list item should immediately turn to blank on same screen, DONE
            - import trash can icon for delete button
        - fix overflow of categories into plan items
        - make plus icon transparent 
        - hook up loader to plan generator, execute async calls on button click, DONE
        - create loader animation while tasks are executing 
       


    - when an item in the plan is clicked, allow for editable time, group votes, and suggestions
        - suggestions should have a refresh button
        - need to fix back button to work
        - configure suggestions list into expandable list view, DONE
        - replace plan item with clicked item, DONE
        - when replace button is pressed, reload item details page, DONE, but crashes with second item added, DONE 
        - when replace button is pressed with blank item, go back to plan page
        - add a responsive feature for when replace button is pressed
        
     
    - send plan to a user

- User preferences: create a page for users to choose the things they like
    - add relevant categores, DONE
    - add check boxes for categories, DONE
    - add function to check boxes, store categories into dictionary with a boolean value, DONE
    - style page better
    
- Main activity for loading new plan and blank plan, DONE
    - include start time, end time, date, DONE
    - style this page better
    - edit start times, end times, locations in a dialog fragment, DONE 


- Group chat function
- Log in screen with create user 
- configure backend with firebase
    - create schema for storing plans, DONE
    - create schema for users and their groups, DONE 
    - add firebase functionality to user generated plans, then to group saved plans
    - create way to to save plans into Firebase, DONE 

- Improve generated plan quality:
    - make sure there is no repeated items
    - fix formatted times, DONE, but test more
    - configure view to handle when the title/categories string gets too long for view 


