package com.goofy.goober.vyasa

import com.goofy.goober.domain.flow.Action

interface UiEventPerformer {
    fun performAction(action: Action.UiAction)
    fun exit()
}
