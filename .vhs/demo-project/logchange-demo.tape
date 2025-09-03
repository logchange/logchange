# VHS tape file for demonstrating logchange usage

Output logchange.gif

Set Shell "bash"
Set FontSize 15
Set Width 1100
Set Height 500
Set PlaybackSpeed 1.2
Set Theme "Dracula"

Set Margin 20
Set MarginFill "#674EFF"
Set BorderRadius 10

Type "logchange - manage changelogs with ease!"
Sleep 1s
Backspace 40

Type "Install logchange using Homebrew, you can use mvn or gradle"
Sleep 1s
Backspace 60

Type "brew install logchange/tap/logchange"
Sleep 2s
Enter
Wait@120s
Sleep 2s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Now, let's initialize project with CLI"
Sleep 1s
Backspace 38

Type "logchange init"
Sleep 2s
Enter
Sleep 1s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Let's see what was created"
Sleep 1s
Backspace 26

Type "ls -la"
Sleep 1s
Enter
Sleep 2s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Add a new changelog entry"
Sleep 1s
Backspace 25

Type "logchange add"
Sleep 2s
Enter
Sleep 1s

Type "01-fix-button"
Sleep 1s
Enter
Sleep 1s

Type "Fix unresponsive login button on main page"
Sleep 1s
Enter
Sleep 1s
Type "5"
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Type "y"
Enter
Sleep 1s
Type "John Doe"
Enter
Sleep 1s
Type "johndoe"
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Enter
Sleep 1s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Let's examine the created entry"
Sleep 1s
Backspace 31

Type "cat changelog/unreleased/01-fix-button.yml"
Sleep 1s
Enter
Sleep 3s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Now, generate the CHANGELOG.md file"
Sleep 1s
Backspace 35

Type "logchange generate"
Sleep 1s
Enter
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Let's see the generated CHANGELOG.md"
Sleep 1s
Backspace 36

Type "cat CHANGELOG.md"
Sleep 1s
Enter
Sleep 4s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Create a release (moves entries from unreleased to version directory)"
Sleep 1s
Backspace 69

Type "logchange release --versionToRelease '1.0.0'"
Sleep 2s
Enter
Sleep 1s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "Check the final structure"
Sleep 1s
Backspace 25

Type "find changelog | head -10"
Sleep 1s
Enter
Sleep 2s
Type "clear"
Sleep 1s
Enter
Sleep 0.75s

Type "View the final CHANGELOG.md with the release"
Sleep 1s
Backspace 44

Type "cat CHANGELOG.md"
Sleep 1s
Enter
Sleep 4s

Type "That's it! Visit https://logchange.dev for more info!"
Sleep 2s