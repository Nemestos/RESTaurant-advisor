<?php

namespace App\Http\Controllers\API;

use App\Http\Controllers\Controller;
use App\Http\Resources\ReviewResource;
use App\Models\Restaurant;
use App\Models\Review;
use Illuminate\Http\Request;

class ReviewsController extends Controller
{
    public function index(Request $request)
    {
        return ReviewResource::collection(Review::all());
    }

    public function get(Request $request, $rest_id)
    {
        $restaurant = Restaurant::find($rest_id);
        return ReviewResource::collection($restaurant->reviews);
    }
}
