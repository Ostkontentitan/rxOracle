
# OracleRX

You are a wanderer on their path to wisdom. On your journey you encounter a temple of oracles, promising the definite answer to all your questions.

## Challenges

1. Model "asking" in a reactive way. A question will be answered after an arbitrary delay.

2. Ask an oracle, then print it's answer. (e.g. "$name says $answer")

3. You find a collection of oracles. Ask them all, and print their answers.

4. Oracles are suddenly democratic, and you need to find their consensus.
    
    a) ask them all at the same time, then print the consensus
    
    b) ask them in sequence.
    
5. To avoid influences of another oracles answer they are comming in one by one to give their answers
    
## Notes

- try not to break the chain unless you don't know better. See hints to improve your solution

- best print results with a linebreak to not collide with the animation println("\n$it")

## Hints

<details>
  <summary>Challenge 1</summary>
  Single.just(...) and the .delay(..., ...) operator are your friends here. 
</details>

<details>
  <summary>Challenge 2</summary>
  how to get name and answer together? .map() and .flatMap() can save the day. 
  <br><br>
  Also again .just() turns out to be useful to get the oracle into the stream. 
</details>

<details>
  <summary>Challenge 3</summary>
  Using Observable.range(..., ...) a stream with any number of oracles can easily be created. 
  <br><br>
  The consuming side just has to swap its flatmap signature to .flatMapSingle() in order to work. 
  (might be different on your solution)
</details>

<details>
  <summary>Challenge 4a</summary>
  There is a trick here:
  <br>
  .scan(..., ...) works like the fold operator in kotlin collections and can be used to sum up the amswers. 
  <br><br>
  the idea is to tread no's as -1, yes as 1, maybe as 0 and just sum up. 
  <br><br>
  .sign (Int) can be used to easily compare the summed up result and map it back to yes, no, maybe
</details>

<details>
  <summary>Challenge 4b</summary>
  getting the answering of questions into sequence is easy and depending on the solution might just require a call to .concatMap instead of .flatMap when doing oracle.ask. 
</details>

<details>
  <summary>Challenge 5</summary>
  whatever
</details>