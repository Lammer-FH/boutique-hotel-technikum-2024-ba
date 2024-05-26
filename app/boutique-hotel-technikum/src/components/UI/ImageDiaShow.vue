<template>
  <ion-row>

    <div class="container">
      <div v-for="(image, index) in images" :key="image.src" :class="{ slides: true, fade: true, active: index === slideIndex }">
        <div class="page">{{ `${index + 1} / ${images.length}` }}</div>
        <ion-img class="image" :src="image.src" />
        <div class="description">{{ image.alt }}</div>
      </div>

      <a class="prev" @click="plusSlides(-1)">&#10094;</a>
      <a class="next" @click="plusSlides(1)">&#10095;</a>
    </div>

    <div class="dot-container">
      <span v-for="(image, index) in images"
            :key="image.src"
            :class="{ dot: true, active: index === slideIndex}"
            @click="currentSlide(index)"></span>
    </div>
  </ion-row>
</template>

<script lang="ts">
export type SlideImageData = {
  src: string,
  alt: string
}

export default {
  props: {
    images: {
      type: Array<SlideImageData>,
      required: true
    }
  },
  data() {
    return {
      slideIndex: 0
    }
  },
  methods: {
    plusSlides(amount: number) {
      const newAmount = (this.slideIndex + amount) % this.images.length;
      if (newAmount < 0) {
        this.slideIndex = this.images.length + newAmount;
      } else {
        this.slideIndex = newAmount;
      }
    },
    currentSlide(index: number) {
      this.slideIndex = index;
    }
  }
}
</script>

<style scoped lang="scss">
ion-row {
  flex-direction: column;
}

.image {
  width: min(100%, 50vh);
}

.container {
  position: relative;
  max-width: 100%;
  margin: auto;

  .page {
    background-color: rgba(0, 0, 0, 0.6);
    color: #f2f2f2;
    font-size: 12px;
    padding: 8px 12px;
    position: absolute;
    top: 0;
  }

  .slides {
    display: none;
    text-align: center;

    &.active {
      display: flex;
      justify-content: center;
    }
  }

  .description {
    background-color: rgba(0, 0, 0, 0.6);
    color: #f2f2f2;
    font-size: 15px;
    padding: 8px 12px;
    position: absolute;
    bottom: 8px;
    width: min(100%, 50vh);
    text-align: center;
  }

  .prev, .next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    margin-top: -22px;
    padding: 16px;
    color: white;
    font-weight: bold;
    font-size: 18px;
    transition: 0.6s ease;
    border-radius: 0 3px 3px 0;
    user-select: none;

    &:hover {
      background-color: rgba(0,0,0,0.8);
    }
  }

  .next {
    right: 0;
    border-radius: 3px 0 0 3px;
  }

  .dot-container {
    text-align: center;
    margin-top: 10px;

    .dot {
      cursor: pointer;
      height: 15px;
      width: 15px;
      margin: 0 2px;
      background-color: #bbb;
      border-radius: 50%;
      display: inline-block;
      transition: background-color 0.6s ease;

      &:hover, &.active {
        background-color: #717171;
      }
    }
  }
}

.fade {
  animation-name: fade;
  animation-duration: 1.5s;
}

@keyframes fade {
  from {opacity: .4}
  to {opacity: 1}
}
</style>