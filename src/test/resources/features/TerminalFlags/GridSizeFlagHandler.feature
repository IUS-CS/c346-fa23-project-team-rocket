Feature: GridSize Flag Handler
    Handles the testing for the GridSizeFlagHandler

    Scenario: We have a 6 by 7 grid and change it to 3 by 9
        Given A TerminalFlagRequest is Created with a 6 by 7 grid and "--grid_width 3 --grid_height 9" command
        When The TerminalFlagRequest is handled by the GridSizeFlagHandler
        Then A TerminalFlagRequest exists with a 3 by 9 grid and "--grid_width 3 --grid_height 9" command

    Scenario Outline: We have a width by height grid and change it to new_width by new height
        Given ATerminalFlagRequest is Created with a <width> by <height> grid and a command to change it to <new_width> by <new_height>
        When The TerminalFlagRequest is handled by the GridSizeFlagHandler
        Then A TerminalFlagRequest exists with a <new_width> by <new_height> grid

        Examples:
            | width | height | new_width | new_height |
            |32     |34      |34         |32          |
            |44     |2       |2          |44          |
            |64     |1       |3          |300         |
            |9000   |9000    |1          |1           |
            |1      |1       |9000       |9000        |
