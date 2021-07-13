# Lightning Recipes Library

## Usage

The library itself doesn't provide any new recipes! It adds a new recipe type
which can be used to create new custom recipes by using datapacks.

In your datapack you will need to place `your_recipe_name.json` file under `/data
/lightning_recipes/recipes/`.

Example `test_recipe.json`:
```json
{
  "type": "lightning_recipes:lightning_recipe",
  "input": {
    "item": "minecraft:grass_block"
  },
  "output": "minecraft:diamond_block"
}
```
In `input` you can also add a block tag to make it work on all blocks with selected tag,
for example:
```json
{
  "type": "lightning_recipes:lightning_recipe",
  "input": {
    "tag": "minecraft:logs"
  },
  "output": "minecraft:cobblestone"
}
```

## License

This library is available by MIT License.

## CurseForge

https://www.curseforge.com/minecraft/mc-mods/lightning-recipes
