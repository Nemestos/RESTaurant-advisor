<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\MenuResource;
use App\Models\Restaurant;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;
use function response;

class MenuController extends Controller
{
    public function index(Request $request, $id)
    {
        $restaurant = Restaurant::find($id);

        return MenuResource::collection($restaurant->menus);
    }

    public function create(Request $request, $id)
    {
        $validator = Validator::make($request->all(), [
            "name" => "required|string",
            "description" => "required|string",
            "price" => "required|numeric|min:1",

        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $restaurant = Restaurant::find($id);

        $menu = $restaurant->menus()->create($validator->validated());
        return new MenuResource($menu);
    }

    public function update(Request $request, $rest_id, $menu_id)
    {
        $validator = Validator::make($request->all(), [
            "name" => "string",
            "description" => "string",
            "price" => "numeric|min:1",
        ]);
        if ($validator->fails()) {
            return response()->json($validator->errors(), 400);
        }
        $restaurant = Restaurant::find($rest_id);

        $menu = $restaurant->menus()->where("id", "=", $menu_id)->first();

        $menu->update($validator->validated());
        return MenuResource::make($menu);
    }

    public function delete(Request $request, $rest_id, $menu_id)
    {

        $restaurant = Restaurant::find($rest_id);

        $menu = $restaurant->menus()->where("id", "=", $menu_id)->first();
        if ($menu->restaurant_id != $restaurant->id) {
            return response()->json(["message" => "this menu doesn't belong to this restaurant"], 400);
        }
        $menu->delete();
        return response()->json(["message" => "successful delete menu" . strval($menu_id)]);
    }

}
