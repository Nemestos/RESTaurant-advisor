<?php

namespace App\Http\Middleware;

use App\Models\Menu;
use App\Models\Restaurant;
use Closure;
use Illuminate\Http\Request;

class CheckMenuRestaurant
{
    /**
     * permet juste de check avec erreur que les ids sont pas invalides
     *
     * @param \Illuminate\Http\Request $request
     * @param \Closure(\Illuminate\Http\Request): (\Illuminate\Http\Response|\Illuminate\Http\RedirectResponse)  $next
     * @return \Illuminate\Http\Response|\Illuminate\Http\RedirectResponse
     */
    public function handle(Request $request, Closure $next)
    {
        $rest_id = true;
        $menu_id = true;
        if ($request->route("id")) {
            $rest_id = Restaurant::where("id", $request->route("id"))->exists();
        } else if ($request->route("rest_id")) {
            $rest_id = Restaurant::where("id", $request->route("rest_id"))->exists();
        }
        if ($request->route("menu_id")) {
            $menu_id = Menu::where("id", $request->route("menu_id"))->exists();
        }
        if ($rest_id && $menu_id) {

            return $next($request);
        }

        return response()->json(["message" => "cant find " . (!$rest_id ? "restaurant" : "menu") . "id"], 400);


    }
}
