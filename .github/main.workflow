workflow "💩💩💩" {
  on = "push"
  resolves = ["action1"]
}

action "action1" {
  uses = "./main-action"
  env = {
    value = "💩"
  }
  args = "\"Hello world, I'm $value\""
}

