<?php

namespace App\Http\Resources;

use App\Models\Restaurant;
use Illuminate\Http\Resources\Json\JsonResource;

class RestaurantResource extends JsonResource
{
    /**
     * Transform the resource into an array.
     *
     * @param \Illuminate\Http\Request $request
     * @return array|\Illuminate\Contracts\Support\Arrayable|\JsonSerializable
     */
    public function toArray($request)
    {
        return [
            "id" => $this->id,
            "name" => $this->name,
            "description" => $this->description,
            "grade" => Restaurant::getMean($this->id),
            "image_url" => $this->image_url != null ? $this->image_url : config("app.default_image_url"),
            "localization" => $this->localization,
            "phone_number" => $this->phone_number,
            "website" => $this->website,
            "hours" => $this->hours,


        ];
    }
}
