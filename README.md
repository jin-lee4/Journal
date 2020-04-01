# Personal Project: Digital Journal

## An organized, accessible way to create and archive journal entries   

**What will the application do?**

*This application will:*
- Create journal entries using text documents
- The user can also add photos to an entry
- Keep an archive of past entries
- Entry archives can be found in chronological order
- User can tag entries as another way to categorize

**Who will use it?**

This application can be used by anyone who is interested in a
simple way to keep a journal in an organized manner.  

**Why is this project of interest to you?**

This project is of interest to me because I am someone who avidly
journals, therefore I would genuinely love to have an application like 
this! I feel this application would be very useful in keeping a record
of one's life without the hassle of a physical book and/or keeping 
it organized. Furthermore, this project will be a great learning
experience.

## User Stories

- As a user, I want to be able to create a new journal entry and add it to 
my collection of entries
- As a user, I want to be able to add text to an entry
- As a user, I want to be able to view all of my journal entries organized
in chronological order
- As a user, I want to be able to select a journal entry from my collection
and view that entry
- As a user, I want to be able to save my journal and the entries when 
I select the quit option
- As a user, I want to be able to load my journal from file when 
the program starts
- As a user, I want to be able to edit the contents of an existing journal entry

##Instructions for Grader
- To generate the first required event (add an entry to journal), click 
"New Entry" button on main scene, create a new entry and click "save" button
- To generate the second required event (view all entries in journal) click 
"View Entries" button on main scene
- To trigger audio component, run the program (start up sound),
save/delete an entry (success sound), click "View" or "Delete" button without
any entries selected on all entries scene (error sound)
- To save the state of my application, click "Quit" button on main scene,
click "Yes" to ConfirmBox, then "Yes" to save
- State of application automatically loads when program is run

##Phase 4: Task 2
- Robust method made in EntriesCollection class for viewEntry method
    - Tests in EntriesCollectionsTest
- Type hierarchy created with -Panel classes in ui class extending AppPanel,
all override abstract display method within AppPanel.

