General idea of how all the things we have so far work:

We have created PollActivity where we host a PollFragment that shows
a poll regarding matters that the user is interested in.

Inside PollFragment, there is an object of the Poll class and we are
basically connecting the widgets of the fragment_poll.xml with our
java code.

PollGenerator is our temporary database, where we create three Poll
objects and we add them to a list of Poll items.

A Poll object contains the poll's title, a related image, a list of
url sources where the user can be informed about the poll and a variable
that tells you whether the user agrees or disagrees with it (this will
be used in the future so we are holding on to this information).

FragmentChangeListener is an interface used inside PollActivity. You can
find more information about it inside its file.

EndPage is just a temporary page to end the app so it doesn't crash
when we vote all the polls of the list.