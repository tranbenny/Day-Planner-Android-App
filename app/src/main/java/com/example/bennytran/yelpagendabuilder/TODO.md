TODO:

Navigation:
- Create a full height navigation drawer for switching between agenda, group messages, user preferences
    - implement navigation bar with icon click and slide, DONE
    - load pages on click, DONE
    - include navigation toolbar/ button click on every screen except start screen, DONE

Base Features:
- Create a group page:
    - add users, group chat with users

- Agenda Page: display time/things to do on a screen

    - computer generated plan and allow for custom user plan
    - include error message when there is no network connection
    - figure out a way to speed up listview loading, don't want to generate new views dynamically

    - get multiple API calls for food/different things to do
        - ISSUE: yelp-android api does not find results with terms other than "food", "restaurant"
            test this issue with the other apis
        - load business info into a model

    - create a prototype plan on the first screen
        - list of pictures with background of restaurant, with text of time interval
            and name of business under it, DONE
        - change font, try roboto font, otherwise import custom type face
        - cache all the background images, DONE
        - create categories for background images


    - when an item in the plan is clicked, allow for editable time, group votes, and suggestions
        - suggestions should have a refresh button
    - send plan to a user

- User preferences: create a page for users to choose the things they like
- Group chat function

Additional Features:
- configuring plans for specific/multiple days?
