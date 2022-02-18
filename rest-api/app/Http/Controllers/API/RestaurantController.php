<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\RestaurantResource;
use App\Models\Restaurant;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Validator;

class RestaurantController extends Controller
{
    public function create(Request $request)
    {
        $validator = Validator::make($request->all(), [
            "name" => "required|string|unique:restaurants",
            "description" => "required|string",
            "grade" => "required|numeric|between:0.0,5.0",
            "localization" => "required|string|max:255",
            "phone_number" => "required|regex:/\+\d{2}\d{9}/",
            "website" => "required|url",
            "hours" => "required|string"
        ]);
        if($validator->fails()){
            return response()->json($validator->errors(),400);
        }
        $restaurant =Restaurant::create($validator->validated());
        return response()->json(["data"=>$restaurant->getAttributes()],201);
    }

    public function index(Request $request)
    {
        return RestaurantResource::collection(Restaurant::all());
    }
}
